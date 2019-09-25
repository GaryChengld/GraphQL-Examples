const getLength = (length, unit) => {
  if (unit === 'FOOT') {
    return length * 3.28084;
  }
  return length;
};

module.exports = { getLength };
