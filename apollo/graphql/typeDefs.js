const { gql } = require('apollo-server-express');

module.exports = gql`
  enum Episode {
    # Star Wars Episode IV: A New Hope, released in 1977.
    NEWHOPE
    # Star Wars Episode V: The Empire Strikes Back, released in 1980.
    EMPIRE
    # Star Wars Episode VI: Return of the Jedi, released in 1983.
    JEDI
  }

  enum LengthUnit {
    METER
    FOOT
  }

  interface Character {
    id: ID!
    name: String!
    friends: [Character]
    appearsIn: [Episode]!
  }

  type Starship {
    id: ID!
    name: String!
    length(unit: LengthUnit = METER): Float
  }

  type Human implements Character {
    id: ID!
    name: String!
    height(unit: LengthUnit = METER): Float
    mass: Float
    friends: [Character]
    appearsIn: [Episode]!
    starships: [Starship]
  }

  type Droid implements Character {
    id: ID!
    name: String!
    friends: [Character]
    appearsIn: [Episode]!
    primaryFunction: String
  }

  type Query {
    about: String!
    starship(id: ID!): Starship
    human(id: ID!): Human
    droid(id: ID!): Droid
    character(id: ID!): Character
  }

  type Mutation {
    setAboutMessage(message: String!): String
  }
`;
