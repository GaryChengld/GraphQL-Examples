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

const starships = [
  {
    id: '3000',
    name: 'Millenium Falcon',
    length: 34.37,
  },
  {
    id: '3001',
    name: 'X-Wing',
    length: 12.5,
  },
  {
    id: '3002',
    name: 'TIE Advanced x1',
    length: 9.2,
  },
  {
    id: '3003',
    name: 'Imperial shuttle',
    length: 20,
  },
];

const starshipData = {};
starships.forEach((ship) => {
  starshipData[ship.id] = ship;
});

const reviewData = {};
reviewData["NEWHOPE"] = [];
reviewData["EMPIRE"] = [];
reviewData["JEDI"] = [];

function getCharacter(id) {
  console.log(`getCharacter, id:${id}`);
  return humanData[id] || droidData[id];
}

function getHero(episode) {
  if (episode === 'EMPIRE') {
    return humanData['1000'];
  }
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

function getFriends(id) {
  console.log(`getFriends, id:${id}`);
  const characters = { ...humanData, ...droidData };
  const character = characters[id];
  if (character) {
    const { friends } = character;
    return friends.map(fid => characters[fid]);
  }
  return null;
}

function getStarship(id) {
  console.log(`getStarship id=${id}`);
  return starshipData[id];
}

function getStarshipsByHumanId(id) {
  console.log(`getStarshipsByHumanId, HumanId=${id}`);
  const human = humanData[id];
  if (human) {
    const { starships } = human;
    return starships.map(sid => starshipData[sid]);
  }
  return null;
}

function search(text) {
  console.log(`search, text:${text}`);
  const re = new RegExp(text, 'i');
  const allData = [...humans, ...droids, ...starships];
  return allData.filter(obj => re.test(obj.name));
}

function createReview(episode, review) {
  console.log(`createReview episode:${episode}, commentary:${review.commentary}`);
  let reviews = reviewData[episode];
  if (reviews) {
    reviews.push(review);
  }
  return review;
}

function getReviews(episode) {
  console.log(`getReviews episode:${episode}`);
  return reviewData[episode];
}

function getDataType(id) {
  if (humanData[id]) {
    return 'Human';
  }
  if (droidData[id]) {
    return 'Droid';
  }
  if (starshipData[id]) {
    return 'Starship';
  }
  return null;
}

module.exports = {
  getCharacter,
  getHuman,
  getDroid,
  getHero,
  getDataType,
  getHero,
  getStarship,
  getFriends,
  getStarshipsByHumanId,
  search,
  createReview,
  getReviews
};
