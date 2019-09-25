const starships = require('../../data/starships');

const getLength = (length, unit) => {
  console.log(`getLength, length:${length}, unit:${unit}`);
  if (unit === 'FOOT') {
    return length * 3.28084;
  }
  return length;
};

module.exports = {
  Query: {
    starship: (_, { id }) => starships.getStarship(id),
  },
  Starship: {
    length: ({ length }, { unit }) => getLength(length, unit),
  },
};
