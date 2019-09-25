const characterData = require('../../data/characters');
const starshipData = require('../../data/starships');
const utils = require('../../utils');

module.exports = {
  Query: {
    human: (_, { id }) => characterData.getHuman(id),
    droid: (_, { id }) => characterData.getDroid(id),
    character: (_, { id }) => characterData.getCharacter(id),
    hero: (_, { episode }) => characterData.getHero(episode),
  },
  Character: {
    __resolveType(data, context, info) {
      const characterType = characterData.getCharacterType(data.id);
      if (characterType) {
        return info.schema.getType(characterType);
      }
      return null;
    },
  },
  Human: {
    height: ({ height }, { unit }) => utils.getLength(height, unit),
    friends: ({ friends }) => friends.map(characterData.getCharacter),
    starships: ({ starships }) => starships.map(starshipData.getStarship),
  },
  Droid: {
    friends: ({ friends }) => friends.map(characterData.getCharacter),
  },
};
