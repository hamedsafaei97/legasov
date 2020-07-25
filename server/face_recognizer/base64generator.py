import base64

with open('DSC_0659.jpg', 'rb') as image:
    st = base64.b64encode(image.read()).decode('utf-8')
    print(st)