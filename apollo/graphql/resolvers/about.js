let aboutMessage = 'Star Wars GraphQL Example v1.0';

async function getMessage() {
  return new Promise((resolve) => setTimeout(resolve(aboutMessage), 0));
}

function setMessage(_, { message }) {
  aboutMessage = message;
  return aboutMessage;
}

module.exports = {
  Query: {
    about: getMessage,
  },
  Mutation: {
    setAboutMessage: setMessage,
  },
};
