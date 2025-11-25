# Dashboard Application

## Features

This application will provide:
* Take control of your finances

## Infraestructure

### Branch Protection

As the main branch "master" will be used for generating releases, this branch must be always in a deployable state, which will be achived by:
* Branch protection rules. Not allowing direct pushes. require up to date branches in pull requests before merging
* Linting. SonarQube Scan (https://sonarcloud.io/project/configuration?id=turanzas_dashboard-app)

### Development

Github actions to trigger automated actions (https://github.com/marketplace?type=actions)

### Continious Integration (CI)
* SonarQube to prevent errors on pushing unreliable code to PRs
* Unit tests with code coverage (80% minimum)
* Integration tests
* Acceptance tests

### Continous Deployment (CD)
* Building image
* Ship image to registry

## Fully automated version management and package publishing

> semantic-release automates the whole package release workflow including: determining the next version number, generating the release notes, and publishing the package.

https://semantic-release.gitbook.io/

Process will be triggered manually from a github action [link] which allows us to have a full control on when to generate a new release.