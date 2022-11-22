req=$(curl -s -X GET http://localhost/container_list -H "Accept: application/json")
echo "${req}"
re='^[0-9]+$' 
if ! [[ $req =~ $re ]] ; then echo "error: Not a number" >&2;
fi