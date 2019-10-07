const dataService = require('../../dataService');
const utils = require('../../utils');

module.exports = {
  Query: {
    human: (_, { id }) => dataService.getHuman(id),
    droid: (_, { id }) => dataService.getDroid(id),
    character: (_, { id }) => dataService.getCharacter(id),
    hero: (_, { episode }) => dataService.getHero(episode),
  },
  Character: {
    __resolveType(data, _, info) {
      const dataType = dataService.getDataType(data.id);
      if (dataType) {
        return info.schema.getType(dataType);
      }
      return null;
    },
  },
  Human: {
    height: ({ height }, { unit }) => utils.getLength(height, unit),
    friends: ({ id }) => dataService.getFriends(id),
    starships: ({ id }) => dataService.getStarshipsByHumanId(id),
  },
  Droid: {
    friends: ({ id }) => dataService.getFriends(id),
  },
};
