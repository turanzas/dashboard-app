Dashboard Application

This application will provide:
- Take control of your finances

Fully automated version management and package publishing

semantic-release automates the whole package release workflow including: determining the next version number, generating the release notes, and publishing the package.

https://semantic-release.gitbook.io/

Process will be triggered manually from a github action [link] which allows us to have a full control on when to release.

As the main branch "main" will be used for generating releases, this branch must be always in a deployable state, which will be achive by:
- Branch protection rules. Not allowing direct pushes. require up to date branches in Github before merging
- Linting. SonarQube Scan (https://sonarcloud.io/project/configuration?id=turanzas_dashboard-app)

Development
- use SonarQube to prevent errors on pushing unreliable code to PRs
- configure github actions to trigger automated actions (https://github.com/marketplace?type=actions)

Continious Integration (CI)
- SonarQube scan
- Unit tests with code coverage (80% minimum)
- Integration tests
- Acceptance tests

Continous Deployment (CD)
- Compiling code / Building image
- Container image
- Generate release
- Ship image to registry
