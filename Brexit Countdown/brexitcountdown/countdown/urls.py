from django.conf.urls import url
from . import views

app_name = 'countdown'

urlpatterns = [
    url(r'^cd/', views.countdown, name='countdown')
]