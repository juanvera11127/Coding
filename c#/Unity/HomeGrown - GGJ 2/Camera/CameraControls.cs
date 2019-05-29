using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraControls : MonoBehaviour
{
    static int xPosition = 0;
    static int yPosition = 0;
    int count = 0;
    float thresh = 0.1f;
    float xDistance = 23f;
    float yDistance = 10f;
    float smoothing = 4f;
    string direction = "";
    public static bool isTransitioning = false;
    bool isMoving = false;
    Vector3 end;
    float time;
    Rigidbody2D rb;

    public Transform target;
    public Vector3 adjust;
    public Vector3 min, max;
    public bool x1, y1, z1;
    public bool isHome;

    void Start()
    {
        rb = gameObject.GetComponent<Rigidbody2D>();
        time = Time.unscaledTime;
    }
    // Update is called once per frame
    void Update()
    {
        if (!isMoving && isHome)
        {
            Vector3 newPos;
            newPos = Camera.main.transform.position;
            if (x1)
                newPos.x = Mathf.Clamp(target.transform.position.x + adjust.x, min.x, max.x);
            if (y1)
                newPos.y = Mathf.Clamp(target.transform.position.y + adjust.y, min.y, max.y);
            if (z1)
                newPos.z = Mathf.Clamp(target.transform.position.z + adjust.z, min.z, max.z);
            Camera.main.transform.position = newPos;
        }

        float x = gameObject.transform.position.x;
        float y = gameObject.transform.position.y;

        if(x % xDistance > xDistance / (x > 0 ? 2 : -2) - thresh && x % xDistance < xDistance / (x > 0 ? 2 : -2) + thresh
            && Time.unscaledTime - time > 1f && !isHome)
        {
            time = Time.unscaledTime;
            if(x > xDistance * xPosition)
            {
                xPosition++;
                direction = "right";
            }
            else
            {
                xPosition--;
                direction = "left";
            }
            isMoving = true;
        }
        if (y % yDistance > yDistance / (y > 0 ? 2 : -2) - thresh && y % yDistance < yDistance / (y > 0 ? 2 : -2) + thresh 
            && Time.unscaledTime - time > 1f && !isHome)
        {
            time = Time.unscaledTime;
            if (y > yDistance * yPosition)
            {
                yPosition++;
                direction = "up";
            }
            else
            {
                yPosition--;
                direction = "down";
            }
            isMoving = true;
        }


        switch (direction)
        {
            case "right" :
                end = new Vector3(Camera.main.transform.position.x + xDistance, Camera.main.transform.position.y, Camera.main.transform.position.z);
                break;
            case "left":
                end = new Vector3(Camera.main.transform.position.x - xDistance, Camera.main.transform.position.y, Camera.main.transform.position.z);
                break;
            case "up":
                end = new Vector3(Camera.main.transform.position.x, Camera.main.transform.position.y + yDistance, Camera.main.transform.position.z);
                break;
            case "down":
                end = new Vector3(Camera.main.transform.position.x, Camera.main.transform.position.y - yDistance, Camera.main.transform.position.z);
                break;
        }
        if(isMoving && !isHome)
        {
            StopAllCoroutines();
            StartCoroutine(Motion(end));
            isMoving = false;
        }

    }
    IEnumerator Motion(Vector3 position)
    {
        isTransitioning = true;
        float t = 0;
        while (t < 1)
        {
            switch(direction)
            {
                case "up":
                    rb.velocity = new Vector3(0, 1, 0);
                    break;
                case "down":
                    rb.velocity = new Vector3(0, -1, 0);
                    break;
                case "left":
                    rb.velocity = new Vector3(-1, 0, 0);
                    break;
                case "right":
                    rb.velocity = new Vector3(1, 0, 0);
                    break;

            }
            t += Time.deltaTime;
            Camera.main.transform.position = Vector3.Lerp(Camera.main.transform.position, position, Time.deltaTime * smoothing);
            yield return null;
        }
        rb.velocity = Vector3.zero;
        isTransitioning = false;
    }
}
