#!/bin/sh

# Credit to Zino Holwerda for his invaluable help in creating this script.

# First get a list of all directories containing a pom.xml file.
output=$(find . -name "pom.xml" -printf "%h\n")
for dir in $output
do
  # For each directory, go into it and run the maven build and testing.
  cd $dir
  mvn integration-test
  exit_code=$?
  # If maven exits with something other than success (0), then exit immediately with that.
  if [ $exit_code != 0 ]
  then
    exit $exit_code
  fi
done
exit 0
