const config = {
  branches: ['main'],
  plugins: [
    "@semantic-release/commit-analyzer",
    "@semantic-release/release-notes-generator",
    /*["@semantic-release/exec", {
        "prepareCmd": "echo ${nextRelease.version} > version.txt"
    }],*/
    ["@semantic-release/git", {
      "message": "chore(release): update version to ${nextRelease.version} [skip ci]\n\n${nextRelease.notes}"
    }],
    "@semantic-release/github",
  ]
};

module.exports = config;
