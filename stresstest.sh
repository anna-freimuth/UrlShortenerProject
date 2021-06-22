#!/usr/bin/env bash

short_urls=(gc gi g5 gh gk g1 g2 gp g7 gu)
host="http://172.20.176.1:8080"
totalCalls=0
while true; do

  short_url=${short_urls[$((RANDOM % ${#short_urls[@]}))]}
  curl -q "$host/$short_url"
  totalCalls=$(($totalCalls + 1))
  echo "Called $totalCalls"
  sleep $((RANDOM % 2))
done
