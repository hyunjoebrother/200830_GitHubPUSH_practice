from django.shortcuts import render

from .models import Blog

# Create your views here.
def index(req):
    return render(req, 'index.html')

def blog(req):
    return render(req, 'blog.html')

def new(req):
    return seventeen
