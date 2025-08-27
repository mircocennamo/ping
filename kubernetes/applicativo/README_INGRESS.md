
# Prima di eseguire ping.yaml occorre creare un Ingress Controller.
# Per esporre il servizio ping-service-nodeport all'esterno del cluster crea un ingress



# Per creare un NGINX Ingress Controller sul tuo cluster Kubernetes, segui questi passaggi:  
# Crea il namespace per l'Ingress Controller:  
    kubectl create namespace ingress-nginx
# Applica la configurazione dell'Ingress Controller:  
    kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
# Verifica l'installazione:  
    kubectl get pods -n ingress-nginx
# Dovresti vedere i pod dell'Ingress Controller in esecuzione nel namespace ingress-nginx.  


# Questi passaggi installeranno l'NGINX Ingress Controller e configureranno un Ingress Resource per esporre il tuo servizio ping-service all'esterno del cluster.