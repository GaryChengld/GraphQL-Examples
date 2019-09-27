const aboutResolver = require('./about');
const starshipResolver = require('./starship');
const characterResolver = require('./character');
const searchResolver = require('./search');
const reviewResolver = require('./review');

function mergeResolvers(...resolvers) {
  let mergedResolvers = {
    Query: {},
    Mutation: {},
  };

  resolvers.forEach((r) => {
    mergedResolvers.Query = { ...mergedResolvers.Query, ...r.Query };
    mergedResolvers.Mutation = { ...mergedResolvers.Mutation, ...r.Mutation };
    const { Query, Mutation, ...others } = r;
    mergedResolvers = { ...mergedResolvers, ...others };
  });
  return mergedResolvers;
}

const resolvers = mergeResolvers(
  aboutResolver,
  starshipResolver,
  characterResolver,
  searchResolver,
  reviewResolver
);
module.exports = resolvers;
