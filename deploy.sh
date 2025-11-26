#!/bin/bash
set -e

########################################
# CONFIGURAÇÕES
########################################
SERVICE_NAME="feedback-app"
IMAGE_TAG="latest"
PROJECT_ID="fiap-adj8-feedback-platform"
REGION="us-central1"
SA_NAME="sa-deploy-feedback-app"
SA_EMAIL="${SA_NAME}@${PROJECT_ID}.iam.gserviceaccount.com"
KEY_PATH="$HOME/gcp-keys/${SA_NAME}-key.json"

ARTIFACT_REPO="feedback-app"
GCP_IMAGE="${REGION}-docker.pkg.dev/${PROJECT_ID}/${ARTIFACT_REPO}/${SERVICE_NAME}:${IMAGE_TAG}"

########################################
# FUNÇÕES
########################################
log() {
  echo -e "[$(date '+%Y-%m-%d %H:%M:%S')] $1"
}

########################################
# 1. Autenticação com SA de Deploy
########################################
log "🔐 Autenticando com Service Account de Deploy..."

if [ ! -f "$KEY_PATH" ]; then
  echo "❌ Key não encontrada em $KEY_PATH"
  exit 1
fi

gcloud auth activate-service-account "$SA_EMAIL" --key-file="$KEY_PATH"
gcloud config set project "$PROJECT_ID"

CURRENT_ACCOUNT=$(gcloud auth list --filter=status:ACTIVE --format="value(account)")
if [[ "$CURRENT_ACCOUNT" != "$SA_EMAIL" ]]; then
  echo "❌ Deploy deve ser executado apenas com $SA_EMAIL"
  exit 1
fi

########################################
# 2. Build da imagem Docker
########################################
log "🐳 Buildando imagem Docker..."
docker build -t ${SERVICE_NAME}:${IMAGE_TAG} .

########################################
# 3. Configurar Docker para GCP
########################################
log "🔧 Configurando Docker para Artifact Registry..."
gcloud auth configure-docker ${REGION}-docker.pkg.dev --quiet

########################################
# 4. Tagging da imagem
########################################
log "🏷️ Tagging da imagem para GCP..."
docker tag ${SERVICE_NAME}:${IMAGE_TAG} ${GCP_IMAGE}

########################################
# 5. Push para Artifact Registry
########################################
log "🚀 Enviando imagem para Artifact Registry..."
docker push ${GCP_IMAGE}

########################################
# 6. Deploy no App Engine
########################################
log "🌍 Deployando no App Engine..."
gcloud app deploy app.yaml \
  --project=${PROJECT_ID} \
  --quiet \
  --promote

########################################
# FINAL
########################################
log "✅ Deploy concluído com sucesso!"
echo "🌎 URL: https://${PROJECT_ID}.appspot.com"
