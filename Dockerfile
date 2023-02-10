# Use an official NGINX image as the base image
FROM nginx

# Opening port for nginx
EXPOSE 80

# Copy the HTML, CSS, and JavaScript files to the NGINX default directory
COPY . /usr/share/nginx/html/

# Copy the asset folder to the NGINX default directory
COPY ./assets/ /usr/share/nginx/html/asset/

# Set the default command to start NGINX
CMD ["nginx", "-g", "daemon off;"]