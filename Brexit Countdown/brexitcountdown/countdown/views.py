from django.shortcuts import render

# Create your views here.

import datetime


def countdown(request):

    brexit = datetime.datetime(2019, 3, 29, 23, 00)
    now = datetime.datetime.now()
    timeleft = brexit - now
    print(divmod(timeleft.total_seconds(), 60))

    return render(request, 'templates/countdown.html', {'timeleft': timeleft.total_seconds()})

