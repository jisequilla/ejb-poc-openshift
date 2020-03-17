#!/bin/sh

INSTALL_DIR="$1"
cp -p "${INSTALL_DIR}"/configuration/application-users.properties $JBOSS_HOME/standalone/configuration/application-users.properties 