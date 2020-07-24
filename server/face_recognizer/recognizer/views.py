import base64

from django.conf import settings
from django.http import HttpResponse, JsonResponse

# Create your views here.
from django.views.decorators.csrf import csrf_exempt
from . import recognize as recognize_face


@csrf_exempt
def recognize(request):
    data = request.POST.get('data')

    if data:
        img_data = base64.b64decode(data)
        tmp_file_name = 'tmp.jpg'
        with open(tmp_file_name, 'wb') as temp:
            temp.write(img_data)
            temp.close()
        file_addr = settings.BASE_DIR + '/' + tmp_file_name
        is_known = recognize_face(file_addr)
        return JsonResponse({'status': is_known}, status=200)
    return JsonResponse({'error': "data field is missing."}, status=400)
