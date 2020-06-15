FROM node:12.7.0-alpine as build_slave
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:1.17.2-alpine as prod_stage
#default path for nginx
COPY --from=build_slave /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]