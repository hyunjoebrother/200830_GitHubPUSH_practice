from django.db import models

# Create your models here.

# Model 생성 : DB에 어떻게 생긴 data를 넣을지 정의

class Blog(models.Model):
    title = models.CharField(max_length = 200)
    created_at = models.DateTimeField(auto_now_add = True)
    body = models.TextField()

    #def __str__(self):
     #   return self.title

# 클래스를 만들고, makemigrations, migrate -> data 전송
# 데이터 보내고 Admin 생성
# Admin.py, Views.py에 클래스(Model) 등록