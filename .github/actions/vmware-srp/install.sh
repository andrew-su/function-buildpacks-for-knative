#!/bin/bash
set -euo pipefail

echo "Installing SRP CLI"

mkdir -p "$HOME/bin"
echo "$HOME/bin" >> "$GITHUB_PATH"

curl \
  --show-error \
  --silent \
  --location \
  "https://srp-cli.s3.amazonaws.com/srp-cli-latest.tgz" \
| tar -C "$HOME/bin" -xz srp

chmod 755 "$HOME/bin/srp"