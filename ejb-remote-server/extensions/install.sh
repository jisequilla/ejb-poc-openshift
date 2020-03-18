#!/bin/sh

INSTALL_DIR="$1"

echo "Running 忍者 カスタム Compilation.. "

${JBOSS_HOME}/bin/add-user.sh -a -u ejbuser -p ejbpassword

echo " User created"