const aboutResolver = require('./about');
const starshipResolver = require('./starship');

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

const resolvers = mergeResolvers(aboutResolver, starshipResolver);
module.exports = resolvers;
