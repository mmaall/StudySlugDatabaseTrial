#!/bin/bash

#Sets environment variable STUDYSLUGDIR as the absolute path to StudySlug's
#directory.
STUDYSLUGDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
export STUDYSLUGDIR
