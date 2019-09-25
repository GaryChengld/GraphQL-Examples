const starships = require('../../data/starships');
const utils = require('../../utils');

module.exports = {
  Query: {
    starship: (_, { id }) => starships.getStarship(id),
  },
  Starship: {
    length: ({ length }, { unit }) => utils.getLength(length, unit),
  },
};
