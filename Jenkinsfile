node {
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/lukwil/Parcelsize.git'
   }
   stage('Build') {
       sh 'gradle build --rerun-tasks'
       //sh 'docker build -t parcelsize ./docker'
       //sh 'cd docker'
       docker.build("parcelsize")
       //sh 'cd ..'
   }
   stage('Integration') {
       // Testumgebung starten
       sh 'vagrant up 7c7a5a9 || true'

       // Image exportieren
       sh 'docker save -o parcelsize.tar parcelsize'
       sh 'docker save -o sfta_tomcat.tar sfta_tomcat'
       sh 'docker save -o sfta_mysql.tar sfta_mysql'

       // Images und JavaScript auf Asset-Server transferieren
       sh 'mv parcelsize.tar /home/lw/assetServer/docker/'
       sh 'mv sfta_tomcat.tar /home/lw/assetServer/docker/'
       sh 'mv sfta_mysql.tar /home/lw/assetServer/docker/'
       sh 'cp js/parcel-size.component.js /home/lw/assetServer/js/'

       // auf Vagrant-Server hochladen
       sh "vagrant ssh 7c7a5a9 -c 'wget -nH -r 192.168.50.1:8083/ -P data'"

       // Docker-Images importieren
       sh 'vagrant ssh 7c7a5a9 -c "sudo docker load -i data/docker/parcelsize.tar"'
       sh 'vagrant ssh 7c7a5a9 -c "sudo docker load -i data/docker/sfta_tomcat.tar"'
       sh 'vagrant ssh 7c7a5a9 -c "sudo docker load -i data/docker/sfta_mysql.tar"'

       // Container starten
       sh 'vagrant ssh 7c7a5a9 -c "sudo docker run -d --network="host" sfta_mysql"'
       sh 'vagrant ssh 7c7a5a9 -c "sudo docker run -d --network="host" sfta_tomcat"'
       sh 'vagrant ssh 7c7a5a9 -c "sudo docker run -d --network="host" parcelsize"'

       // Datenbankverbindung testen, sleep 30 als Timeout bis DB gestartet ist
       sh 'vagrant ssh 7c7a5a9 -c "chmod u+x data/dbTest && sleep 30 && data/dbTest"'
   }
   stage('UAT') {
       sh 'vagrant ssh 7c7a5a9 -c "sudo chmod u+x data/geckodriver"'
       sh 'vagrant ssh 7c7a5a9 -c "sudo rm -R -f Parcelsize"'
       sh 'vagrant ssh 7c7a5a9 -c "git clone https://github.com/lukwil/Parcelsize.git"'
       sh 'vagrant ssh 7c7a5a9 -c "cd Parcelsize/ && chmod u+x ./gradlew && ./gradlew uatTests --rerun-tasks"'
       sh 'vagrant ssh 7c7a5a9 -c "cd Parcelsize/ && ./gradlew gatlingRun --rerun-tasks"'

       sh 'vagrant halt 7c7a5a9'
   }
   stage('Manual Test') {
       // Testumgebung starten
       sh 'vagrant up 7fdbe4e || true'

       // auf Vagrant-Server hochladen
       sh "vagrant ssh 7fdbe4e -c 'wget -nH -r 192.168.50.1:8083/ -P data'"

       // Docker-Images importieren
       sh 'vagrant ssh 7fdbe4e -c "sudo docker load -i data/docker/parcelsize.tar"'
       sh 'vagrant ssh 7fdbe4e -c "sudo docker load -i data/docker/sfta_tomcat.tar"'
       sh 'vagrant ssh 7fdbe4e -c "sudo docker load -i data/docker/sfta_mysql.tar"'

       // Container starten
       sh 'vagrant ssh 7fdbe4e -c "sudo docker run -d --network="host" sfta_mysql"'
       sh 'vagrant ssh 7fdbe4e -c "sudo docker run -d --network="host" sfta_tomcat"'
       sh 'vagrant ssh 7fdbe4e -c "sudo docker run -d --network="host" parcelsize"'

       sh 'vagrant ssh 7fdbe4e -c "sudo chmod u+x data/geckodriver"'
       sh 'vagrant ssh 7fdbe4e -c "sudo rm -R -f Parcelsize"'
       sh 'vagrant ssh 7fdbe4e -c "git clone https://github.com/lukwil/Parcelsize.git"'
       sh 'vagrant ssh 7fdbe4e -c "cd Parcelsize/ && chmod u+x ./gradlew && ./gradlew uatTests --rerun-tasks"'
       sh 'vagrant ssh 7fdbe4e -c "cd Parcelsize/ && ./gradlew gatlingRun --rerun-tasks"'

            script {
                def manualTestsSuccessfull = input(message: 'Manual test successful?', ok: 'Yes',
                        parameters: [booleanParam(defaultValue: true,
                        description: 'Please release the images',name: 'Yes?')])
            }
        sh 'vagrant halt 7fdbe4e'
    }
    stage('Release') {
       // Produmgebung starten
       sh 'vagrant up b8dc86e || true'

       sh "vagrant ssh b8dc86e -c 'wget -nH -r 192.168.50.1:8083/ -P data'"

       // Docker-Images importieren
       sh 'vagrant ssh b8dc86e -c "sudo docker load -i data/docker/parcelsize.tar"'
       sh 'vagrant ssh b8dc86e -c "sudo docker load -i data/docker/sfta_tomcat.tar"'
       sh 'vagrant ssh b8dc86e -c "sudo docker load -i data/docker/sfta_mysql.tar"'

       // Container starten
       sh 'vagrant ssh b8dc86e -c "sudo docker run -d --network="host" sfta_mysql"'
       sh 'vagrant ssh b8dc86e -c "sudo docker run -d --network="host" sfta_tomcat"'
       sh 'vagrant ssh b8dc86e -c "sudo docker run -d --network="host" parcelsize"'
    }
}