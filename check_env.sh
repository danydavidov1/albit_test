#!/bin/bash
sleep 10
get=$(curl -s -X GET http://localhost/container_list -H "Accept: application/json")
echo "${get}"
num='^[0-9]+$'
if ! [[ $get =~ $num ]] ; then 
    echo "error: Not a number" >&2; exit 1
fi
