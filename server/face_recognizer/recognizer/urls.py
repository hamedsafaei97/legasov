from django.urls import path

from recognizer.views import recognize

urlpatterns = [
    path('recognize/', recognize),
]
