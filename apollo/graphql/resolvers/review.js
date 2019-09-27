const dataService = require('../../dataService');

module.exports = {
  Query: {
    reviews: (_, { episode }) => dataService.getReviews(episode),
  },
  Mutation: {
    createReview: (_, { episode, review }) => dataService.createReview(episode, review),
  },
};
