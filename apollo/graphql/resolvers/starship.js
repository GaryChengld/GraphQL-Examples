const dataService = require('../../dataService');
const utils = require('../../utils');

module.exports = {
  Query: {
    starship: (_, { id }) => dataService.getStarship(id),
  },
  Starship: {
    length: ({ length }, { unit }) => utils.getLength(length, unit),
  },
};
