#!/bin/bash
set -e

if ([ "$TRAVIS_BRANCH" == "master" ] || [ ! -z "$TRAVIS_TAG" ]) &&
    [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
  $TRAVIS_BUILD_DIR/mvnw deploy --settings $TRAVIS_BUILD_DIR/.travis/settings.xml -DskipTests=true -B -V
fi