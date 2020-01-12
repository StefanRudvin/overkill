#!/bin/bash

echo "Starting overkill..."

cd k8s
echo ""
echo "->  Setting up ZK and Kafka..."
echo ""
kubectl create -f zk-deployment.yaml
kubectl create -f zk-service.yaml
kubectl create -f kafka-deployment.yaml
kubectl create -f kafka-service.yaml
echo ""
echo "->  Setting up Cassandra..."
echo ""
kubectl create -f cassandra-data.yaml
kubectl create -f cassandra-service.yaml
kubectl create -f cassandra-statefulset.yaml
