# Installs the current application on a Node Image.
FROM node:10.5
# Install angular
RUN npm install -g @angular/cli
# Sets the path where the app is going to be installed
ENV NODE_ROOT /usr/app/
# Creates the directory and all the parents (if they don’t exist)
RUN mkdir -p $NODE_ROOT
# Sets the /usr/app as the active directory
WORKDIR $NODE_ROOT

# Install Dependencies
COPY package.json ./package.json
COPY package-lock.json ./package-lock.json
RUN npm install

# ng serve port
EXPOSE 4200

# Copies all the content
COPY . .

# Generate dist
RUN ng build

