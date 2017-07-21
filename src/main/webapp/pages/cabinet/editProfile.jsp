<div class="width100p height-auto">
    <div class="float-l width50p">
        <form action="/changeAvatar" class="contactForm padding-t-b-2p" method="post" enctype="multipart/form-data">
            <input class="float-l" type="submit" value="Change Avatar">
            <input class="float-r" name="avatar" type="file">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">
        </form>

    </div>
    <div class="float-r width50p">
        <img src="/images/bender.png" width="100%" height="600px">
    </div>
</div>
