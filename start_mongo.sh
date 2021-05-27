#!/bin/bash

mkdir $PWD/mongo_data
sudo docker run -d -p 27017:27017 -v $PWD/mongo_data:/data/db mongo
