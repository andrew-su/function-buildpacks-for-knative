# Instructions

## Requirements

- knative serving and eventing installed
- knative cli (kn) installed
- kubectl installed
- a namespace with the name event-sample

## Build image with buildpacks

`pack build producer-java -B ghcr.io/vmware-tanzu/function-buildpacks-for-knative/functions-builder:0.0.10`

## Tag and push image

`docker tag producer-java gcr.io/lfabian-sandbox/producer-java`

`docker push gcr.io/lfabian-sandbox/producer-java`

## Create kn service with built image

`kn service create myproducer --image gcr.io/lfabian-sandbox/producer-java -n event-sample`
