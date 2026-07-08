# backend




Builda il progetto:

```bash
mvn clean package
```

Creazione immagine OCI
```bash
mvn spring-boot:build-image
```
verifica:
```bash
docker images
```
Esporta l'immagine:
```bash
docker save ping-service:0.0.1-SNAPSHOT -o  ping-service.tar
```

Importala in containerd:
```bash
sudo k3s ctr images import ping-service.tar
```
verifica:
```bash
sudo k3s ctr images ls | grep ping-service
```


applica deployment:
```bash
kubectl apply -f openshift-deployment.yaml
```

log
```bash
docker logs spring-demo
```

Verifica che il pod sia in esecuzione:
```bash
kubectl get pods
```     

Verifica che il servizio sia in esecuzione:
```bash
kubectl get all
kubectl get ingress
```



                  +----------------+
                  |  API Gateway   |
                  +--------+-------+
                           |
                           | https://ping-service.company.it
                           v
                  +----------------+
                  |    Ingress     |
                  |   (Traefik)    |
                  +--------+-------+
                           |
                           v
                  +----------------+
                  | K8S Service    |
                  |   ClusterIP    |
                  +--------+-------+
                           |
         +-----------------+-----------------+
         |                                   |
         v                                   v
+-------------------+             +-------------------+
| Pod Backend #1    |             | Pod Backend #2    |
+-------------------+             +-------------------+


               
Flusso della richiesta 

1. Il client invia una richiesta HTTPS a `https://ping-service.company.it`.
2. L'API Gateway riceve la richiesta e la inoltra all'Ingress (Traefik).
3. L'Ingress riceve la richiesta e la inoltra al servizio Kubernetes (ClusterIP) associato al backend.
4. Il servizio Kubernetes riceve la richiesta e la inoltra a uno dei pod backend disponibili (Pod Backend #1 o Pod Backend #2) in base alla strategia di bilanciamento del carico.
5. Il pod backend selezionato elabora la richiesta e genera una risposta.
6. La risposta viene inviata indietro attraverso il servizio Kubernetes, l'Ingress e l'API Gateway fino al client.   
7. Il client riceve la risposta finale dal backend.
8. Il flusso della richiesta garantisce che le richieste vengano gestite in modo sicuro e bilanciato tra i pod backend disponibili, consentendo scalabilità e alta disponibilità del servizio.
9. In caso di guasto di un pod backend, il servizio Kubernetes reindirizza automaticamente le richieste ai pod rimanenti, garantendo la continuità del servizio.
10. L'API Gateway può implementare ulteriori funzionalità come autenticazione, autorizzazione, caching e monitoraggio delle richieste per migliorare la sicurezza e le prestazioni complessive del sistema.
11. In sintesi, il flusso della richiesta attraverso l'API Gateway, l'Ingress e il servizio Kubernetes consente di gestire in modo efficiente le richieste dei client, garantendo scalabilità, alta disponibilità e sicurezza del backend.
            

                    API Gateway
                          |
                          |  GET /ping
                          v
            https://ping-service.company.it
                          |
                          v
                        Ingress (Traefik)
                          |
                          v
                    Service ping-service
                           |
                        +--> Pod Backend #1
                           |
                        +--> Pod Backend #2



Openshift Deployment YAML:

                +-------------------+
                |   API Gateway     |
                | (Apigee, IBM, ...)|
                +---------+---------+
                          |
                          |
                          v
                ping.company.it
                          |
                          v
                +-------------------+
                | OpenShift Route   |
                +---------+---------+
                          |
                          v
                +-------------------+
                | Service ClusterIP |
                +---------+---------+
                          |
            +-------------+-------------+
            |                           |
            v                           v
      +-----------+               +-----------+
      |  Pod #1   |               |  Pod #2   |
      +-----------+               +-----------+