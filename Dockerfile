FROM python:3.10-slim-bullseye

WORKDIR /app

COPY ./requirements.txt . 

RUN pip install -r requirements.txt

COPY ./main.py .

EXPOSE 5000

ENTRYPOINT ["python"]

CMD ["main.py"]
