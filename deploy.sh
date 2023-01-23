#!/bin/bash

heroku git:remote -a hotelprogi &&
git subtree push --prefix frontend/ heroku dev:master &&
heroku git:remote -a hotelprogibackend &&
git subtree push --prefix backend/ heroku dev:master
