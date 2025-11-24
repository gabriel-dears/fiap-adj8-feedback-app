#!/bin/bash
set -e

########################################
# CONFIGURAÇÕES
########################################
SERVICE_NAME="feedback-app"
IMAGE_TAG="latest"
PROJECT_ID="fiap-adj8-feedback-platform"
REGION="us-central1"

########################################
# 0. Build local da imagem Docker
########################################
echo "🐳 Buildando imagem Docker localmente..."
docker build -t ${SERVICE_NAME}:${IMAGE_TAG} .

########################################
# 1. Tag para Artifact Registry do GCP
########################################
GCP_REPO="us-central1-docker.pkg.dev/${PROJECT_ID}/${SERVICE_NAME}/${SERVICE_NAME}"
echo "🏷️ Tagging Docker image: ${GCP_REPO}:${IMAGE_TAG}"
docker tag ${SERVICE_NAME}:${IMAGE_TAG} ${GCP_REPO}:${IMAGE_TAG}

########################################
# 2. Push para Artifact Registry
########################################
echo "🚀 Enviando imagem para Artifact Registry..."
docker push ${GCP_REPO}:${IMAGE_TAG}

########################################
# 3. Deploy no App Engine Flexível
########################################
echo "🌐 Deploy no App Engine..."
gcloud app deploy app.yaml --project ${PROJECT_ID} --quiet

########################################
# FINAL
########################################
echo "✅ Deploy concluído!"
echo "Acesse a aplicação: https://${PROJECT_ID}.appspot.com"
