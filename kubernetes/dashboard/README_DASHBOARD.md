# Installare il Kubernetes Dashboard
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.6.0/aio/deploy/recommended.yaml
# Controlla lo Stato del Dashboard
kubectl get pods -n kubernetes-dashboard
# Dovresti vedere i pod del Dashboard in stato Running. Se ci sono problemi, controlla i log del pod:
kubectl logs -n kubernetes-dashboard <nome-del-pod>
# Controlla se il servizio kubernetes-dashboard è stato creato correttamente:
kubectl get services -n kubernetes-dashboard
# Dovresti vedere un servizio chiamato kubernetes-dashboard. Se non lo vedi, significa che l'installazione non è riuscita.
# Verifica il Namespace
kubectl get namespaces

# Creare un Service Account
# Autenticazione al Dashboard
kubectl apply -f .\service-account.yaml

kubectl create token dashboard-admin-sa -n kubernetes-dashboard

# starting kubernetes dashboard
kubectl proxy
# Accedi al Dashboard
# Per accedere al Dashboard, apri il tuo browser e vai all'URL:
# http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

#url dashboard kubernetes
http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/
