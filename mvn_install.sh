#!/bin/bash

cd Database ;
mvn install ;
cd ..

cd BoutiqueEnLigne/jsf-webflow-persistence ;
mvn install ;
cd ../..

cd WebService ;
mvn install ;
cd ..

cd BoutiqueEnLigne ;
mvn install ;
cd ..
