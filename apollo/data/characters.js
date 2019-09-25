const humans = [
  {
    id: '1000',
    name: 'Luke Skywalker',
    friends: ['1002', '1003', '2000', '2001'],
    appearsIn: ['NEWHOPE', 'EMPIRE', 'JEDI'],
    height: 1.72,
    mass: 77,
    starships: ['3001', '3003'],
  },
  {
    id: '1001',
    name: 'Darth Vader',
    friends: ['1004'],
    appearsIn: ['NEWHOPE', 'EMPIRE', 'JEDI'],
    height: 2.02,
    mass: 136,
    starships: ['3002'],
  },
  {
    id: '1002',
    name: 'Han Solo',
    friends: ['1000', '1003', '2001'],
    appearsIn: ['NEWHOPE', 'EMPIRE', 'JEDI'],
    height: 1.8,
    mass: 80,
    starships: ['3000', '3003'],
  },
  {
    id: '1003',
    name: 'Leia Organa',
    friends: ['1000', '1002', '2000', '2001'],
    appearsIn: ['NEWHOPE', 'EMPIRE', 'JEDI'],
    height: 1.5,
    mass: 49,
    starships: [],
  },
  {
    id: '1004',
    name: 'Wilhuff Tarkin',
    friends: ['1001'],
    appearsIn: ['NEWHOPE'],
    height: 1.8,
    mass: null,
    starships: [],
  },
];

const humanData = {};
humans.forEach((human) => {
  humanData[human.id] = human;
});

const droids = [
  {
    id: '2000',
    name: 'C-3PO',
    friends: ['1000', '1002', '1003', '2001'],
    appearsIn: ['NEWHOPE', 'EMPIRE', 'JEDI'],
    primaryFunction: 'Protocol',
  },
  {
    id: '2001',
    name: 'R2-D2',
    friends: ['1000', '1002', '1003'],
    appearsIn: ['NEWHOPE', 'EMPIRE', 'JEDI'],
    primaryFunction: 'Astromech',
  },
];

const droidData = {};
droids.forEach((droid) => {
  droidData[droid.id] = droid;
});

function getCharacter(id) {
  console.log(`getCharacter, id:${id}`);
  return humanData[id] || droidData[id];
}

function getHero(episode) {
  if (episode === 'EMPIRE') {
    // Luke is the hero of Episode V.
    return humanData['1000'];
  }
  // Artoo is the hero otherwise.
  return droidData['2001'];
}

function getHuman(id) {
  console.log(`getHuman, id:${id}`);
  return humanData[id];
}

function getDroid(id) {
  console.log(`getDroid, id:${id}`);
  return droidData[id];
}

function getHero(episode) {
  if (episode === 'EMPIRE') {
    // Luke is the hero of Episode V.
    return humanData['1000'];
  }
  // Artoo is the hero otherwise.
  return droidData['2001'];
}

function getCharacterType(id) {
  if (humanData[id]) {
    return 'Human';
  }
  if (droidData[id]) {
    return 'Droid';
  }
  return null;
}

module.exports = {
  getCharacter,
  getHuman,
  getDroid,
  getHero,
  getCharacterType,
  getHero
};
