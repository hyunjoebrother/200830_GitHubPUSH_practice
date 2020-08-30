from django.shortcuts import render

from .models import Blog

# Create your views here.
def index(req):
    return render(req, 'index.html')

def blog(req):
    reurn render(req, 'blog.html')
