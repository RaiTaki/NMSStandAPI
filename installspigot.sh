#!/usr/bin/env bash
mkdir -p $HOME/spigot-build
pushd $HOME/spigot-build
wget https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar -O $HOME/spigot-build/BuildTools.jar
git config --global --unset core.autocrlf
java -Xmx1500M -jar BuildTools.jar --rev $1 | grep Installing
mvn install:install-file -Dfile=$HOME/spigot-build/spigot-$1.jar -Dpackaging=jar -DpomFile=$HOME/spigot-build/Spigot/Spigot-Server/pom.xml
mvn install:install-file -Dfile=$HOME/spigot-build/craftbukkit-$1.jar -Dpackaging=jar -DpomFile=$HOME/spigot-build/CraftBukkit/pom.xml
popd