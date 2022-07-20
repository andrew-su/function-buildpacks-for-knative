has_srp_creds=$(test "${CLIENT_ID}" &> /dev/null && test "${CLIENT_SECRET}" &> /dev/null && echo 'true')
echo "::set-output name=has-srp-creds::${has_srp_creds}"