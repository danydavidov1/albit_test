#!/bin/bash
get=$(curl -s -X GET http://localhost/container_list -H "Accept: application/json")
echo "${get}"
num='^[0-9]+$'
if ! [[ $get =~ $num ]] ; then echo "error: Not a number" >&2;
fi
echo "${get}"
echo "${num}"
