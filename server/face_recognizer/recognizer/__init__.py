from os import listdir
from os.path import join, isfile

# import face_recognition
from django.conf import settings

STATIC_DIR = settings.BASE_DIR + '/static/'

images = [(join(STATIC_DIR, f), f[:-4]) for f in listdir(STATIC_DIR)
          if isfile(join(STATIC_DIR, f))]

image_db = {}

# for img_addr, person_name in images:
#     known_image = face_recognition.load_image_file(img_addr)
#     biden_encoding = face_recognition.face_encodings(known_image)[0]
#     image_db[person_name] = [biden_encoding]


def recognize(unknown_img_addr):
    # unknown_image = face_recognition.load_image_file(unknown_img_addr)
    # encoding = face_recognition.face_encodings(unknown_image)[0]
    # for person in image_db:
    #     res = face_recognition.compare_faces(image_db[person], encoding)
    #     if res[0]:
    #         return True
    return True
