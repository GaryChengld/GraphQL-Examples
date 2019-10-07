const dataService = require('../../dataService');

module.exports = {
  Query: {
    search: (_, {text}) => dataService.search(text),
  },
  SearchResult: {
    __resolveType(data, _, info) {
      const dataType = dataService.getDataType(data.id);
      if (dataType) {
        return info.schema.getType(dataType);
      }
      return null;
    }
  }
};