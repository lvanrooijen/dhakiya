FROM node:20.12.2

WORKDIR /app

COPY package*.json ./
RUN yarn install

COPY . .

EXPOSE 3000

CMD ["yarn", "sparkitup"]
