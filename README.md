# backend

Start the application:
```bash
mvn spring-boot:run
```
# STEP per creare immagine docker e push su dockerhub
docker build -t ping-service .
docker login
docker tag ping-service mircocennamo/miorepo:ping-service
docker push mircocennamo/miorepo:ping-service

kubectl apply -f kubernetes/ping.yaml

# creazione token admin per accesso alla dashboard
# Installare il Kubernetes Dashboard
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.6.0/aio/deploy/recommended.yaml
# Controlla lo Stato del Dashboard
kubectl get pods -n kubernetes-dashboard
# Dovresti vedere i pod del Dashboard in stato Running. Se ci sono problemi, controlla i log del pod:
kubectl logs -n kubernetes-dashboard <nome-del-pod>
# Controlla se il servizio kubernetes-dashboard è stato creato correttamente:
kubectl get services -n kubernetes-dashboard
Dovresti vedere un servizio chiamato kubernetes-dashboard. Se non lo vedi, significa che l'installazione non è riuscita.
# Verifica il Namespace
kubectl get namespaces

# Creare un Service Account
# Autenticazione al Dashboard
kubectl apply -f .\service-account.yaml

kubectl create token dashboard-admin-sa -n kubernetes-dashboard



Usare Port Forwarding
Puoi utilizzare il comando kubectl port-forward per reindirizzare una porta locale a un pod o a un servizio. Ad esempio, per reindirizzare la porta 8090 del tuo computer alla porta 8089 del servizio ping-service-nodeport nel namespace test, esegui il seguente comando:

kubectl port-forward svc/ping-service-nodeport 8090:8089 -n test


#ip del nodo
kubectl get nodes -o wide
ip_del_nodo:ports.NodePort per accedere al pod dall'interno del cluster


#esempi di chiamate
curl http://ping-service-nodeport:8089/api/ping
curl http://ping-service-nodeport.test.svc.cluster.local:8089/api/ping

# Service con Load Balancing
# I Service in Kubernetes utilizzano il bilanciamento del carico (load balancing) automaticamente. Se hai più istanze di un Pod dietro lo stesso Service, le chiamate # REST verranno distribuite tra i vari Pod. Questo garantisce una distribuzione equa del carico.



Namespace Differenti
Se i tuoi Pod si trovano in namespace differenti, dovrai specificare il namespace nella chiamata REST. Per esempio, se my-rest-service si trova nel namespace production, la chiamata sarà:

curl http://my-rest-service.production.svc.cluster.local/endpoint
# Considera l'uso di Ingress per esporre le API REST al di fuori del cluster.
# Proteggi le comunicazioni REST utilizzando TLS e configurando politiche di rete appropriate.

#starting kubernetes dashboard
kubectl proxy
# Accedi al Dashboard
# Per accedere al Dashboard, apri il tuo browser e vai all'URL:
# http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

#url dashboard kubernetes
http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/


# per esporre il servizio ping-service-nodeport all'esterno del cluster crea un ingress

# Per creare un NGINX Ingress Controller sul tuo cluster Kubernetes, segui questi passaggi:  
# Crea il namespace per l'Ingress Controller:  
    kubectl create namespace ingress-nginx
# Applica la configurazione dell'Ingress Controller:  
    kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
# Verifica l'installazione:  
    kubectl get pods -n ingress-nginx
# Dovresti vedere i pod dell'Ingress Controller in esecuzione nel namespace ingress-nginx.  
# Crea un Ingress Resource: Ecco un esempio di configurazione di un Ingress Resource per esporre un servizio chiamato ping-service:  

    
  Applica questa configurazione con il comando:  
  kubectl apply -f k8s-ingress.yaml
  Questi passaggi installeranno l'NGINX Ingress Controller e configureranno un Ingress Resource per esporre il tuo servizio ping-service all'esterno del cluster.